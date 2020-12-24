<template>
  <div id="default-layout">
    <b-navbar toggleable="lg" type="dark" variant="primary">
      <b-navbar-brand :to="localePath('/')">
        {{ $t('usermanager') }}
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse" />
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <div v-if="isLoggedIn">
            <nuxt-link tag="b-nav-item" :to="localePath('/')">
              {{ $t('nav.dashboard') }}
            </nuxt-link>
          </div>
          <div v-else>
            <nuxt-link tag="b-nav-item" :to="localePath('/auth/login')">
              {{ $t('nav.login') }}
            </nuxt-link>
          </div>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown :text="$t('nav.language')" right>
            <b-dropdown-item
              v-for="locale in locales"
              :key="locale.code"
              :active="isActiveLocale(locale.code)"
              @click="changeLocale(locale.code)"
            >
              {{ formatLocale(locale) }}
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <div id="router">
      <Nuxt />
    </div>
  </div>
</template>

<script>
export default {
  name: 'DefaultLayout',
  computed: {
    isLoggedIn() {
      return this.$store.state.auth.isLoggedIn
    },
    locales() {
      return this.$i18n.locales
    }
  },
  methods: {
    isActiveLocale(code) {
      return this.$i18n.locale === code
    },
    changeLocale(code) {
      this.$i18n.setLocale(code)
    },
    formatLocale(locale) {
      return locale.name
    }
  }
}
</script>

<style></style>
