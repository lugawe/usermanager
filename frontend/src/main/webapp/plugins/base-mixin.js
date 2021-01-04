import Vue from 'vue'

if (!Vue.__base_mixin__) {
  Vue.__base_mixin__ = true
  const base = {
    computed: {
      isLoggedIn() {
        return this.$store.state.auth.isLoggedIn
      },
      user() {
        return this.isLoggedIn ? this.$store.state.auth.user : {}
      }
    },
    methods: {
      gotoPage(path) {
        if (path) {
          const localePath = this.localePath(path)
          if (this.$route.path !== localePath) {
            this.$router.push({ path: localePath })
          }
        }
      },
      gotoLoginPage() {
        this.gotoPage('/auth/login')
      },
      gotoMainPage() {
        this.gotoPage('/')
      },
      toast(toastTitle, toastText, toastAutoHideDelay, toastVariant) {
        this.$bvToast.toast(toastText, {
          title: toastTitle,
          autoHideDelay: toastAutoHideDelay,
          solid: true,
          variant: toastVariant
        })
      },
      info(toastTitle, toastText, toastAutoHideDelay = 5000) {
        this.toast(toastTitle, toastText, toastAutoHideDelay, 'default')
      },
      error(toastTitle, toastText, toastAutoHideDelay = 5000) {
        this.toast(toastTitle, toastText, toastAutoHideDelay, 'danger')
      }
    }
  }
  Vue.mixin(base)
}
