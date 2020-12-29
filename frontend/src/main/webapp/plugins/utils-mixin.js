import Vue from 'vue'

if (!Vue.__utils_mixin__) {
  Vue.__utils_mixin__ = true
  const utils = {
    methods: {
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
  Vue.mixin(utils)
}
