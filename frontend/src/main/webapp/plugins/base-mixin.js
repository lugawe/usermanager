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
      gotoLoginPage() {
        this.$router.push({ path: this.localePath('/auth/login') })
      },
      gotoMainPage() {
        this.$router.push({ path: this.localePath('/') })
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
