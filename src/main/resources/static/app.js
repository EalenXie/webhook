const {createApp} = Vue;

createApp({
    data() {
        return {
            webhooks: [],
            loadingWebhooks: true,
            webhookError: null,
            messages: [],
            loadingMessages: false
        };
    },

    mounted() {
        this.loadWebhooks();
        this.loadHistory();
        this.connectWebSocket();
    },

    methods: {
        renderMarkdown(md) {
            if (!md) return '';
            return marked.parse(md, {
                breaks: true,
                gfm: true
            });
        },
        loadWebhooks() {
            fetch("/actuator/webhooks/configs", {
                credentials: "include"
            })
                .then(res => res.json())
                .then(data => {
                    this.webhooks = data;
                    this.loadingWebhooks = false;
                })
                .catch(() => {
                    this.webhookError = "Webhook 数据加载失败";
                    this.loadingWebhooks = false;
                });
        },
        copyToClipboard(text) {
            if (!text) return;

            if (navigator.clipboard && navigator.clipboard.writeText) {
                navigator.clipboard.writeText(text)
                    .then(() => this.showCopied(text))
                    .catch(() => this.fallbackCopy(text));
            } else {
                this.fallbackCopy(text);
            }
        },
        fallbackCopy(text) {
            try {
                const textarea = document.createElement("textarea");
                textarea.value = text;
                textarea.style.position = "fixed";
                textarea.style.opacity = "0";
                document.body.appendChild(textarea);
                textarea.select();
                document.execCommand("copy");
                document.body.removeChild(textarea);
                this.showCopied(text);
            } catch (err) {
                console.error("复制失败", err);
            }
        },
        showCopied(text) {
            const w = this.webhooks.find(item => item.webhookUrl === text);
            if (w) {
                w.copied = true;
                setTimeout(() => w.copied = false, 1500);
            }
        },
        loadHistory() {
            this.loadingMessages = true;
            fetch('/actuator/webhooks/message/history')
                .then(res => res.json())
                .then(data => {
                    this.messages = data.reverse();
                })
                .finally(() => this.loadingMessages = false);
        },
        connectWebSocket() {
            const socket = new SockJS('/ws');
            const stomp = Stomp.over(socket);

            stomp.connect({}, () => {
                stomp.subscribe('/topic/messages', msg => {
                    this.messages.push(JSON.parse(msg.body));
                    this.scrollToBottom();
                });
            });
        },
        scrollToBottom() {
            this.$nextTick(() => {
                const el = this.$refs.logContainer;
                if (el) el.scrollTop = el.scrollHeight;
            });
        }
    }
}).mount("#app");