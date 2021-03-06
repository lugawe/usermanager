const availableLocales = [
  {
    code: 'en-US',
    name: 'English',
    file: 'en-US.json'
  }
]

export default {
  ssr: false,
  target: 'static',
  srcDir: 'src/main/webapp',
  head: {
    title: 'usermanager',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' }
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }]
  },
  css: [],
  plugins: ['~/plugins/auth-plugin', '~/plugins/base-mixin'],
  components: true,
  buildModules: ['@nuxtjs/eslint-module'],
  modules: ['bootstrap-vue/nuxt', '@nuxtjs/axios', 'nuxt-i18n'],
  axios: {
    proxy: true
  },
  proxy: {
    '/api/': { target: 'http://localhost:9000/api', pathRewrite: { '^/api/': '' } }
  },
  i18n: {
    locales: availableLocales,
    defaultLocale: 'en-US',
    lazy: true,
    langDir: 'locales/'
  },
  build: {}
}
