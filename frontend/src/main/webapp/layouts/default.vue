<template>
  <div id="default-layout">
    <b-navbar toggleable="lg" type="dark" variant="primary">
      <b-navbar-brand :to="localePath('/')">
        {{ $t('usermanager') }}
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse" />
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav v-if="isLoggedIn">
          <nuxt-link tag="b-nav-item" :to="localePath('/')">
            {{ $t('nav.dashboard') }}
          </nuxt-link>
        </b-navbar-nav>
        <b-navbar-nav v-else>
          <nuxt-link tag="b-nav-item" :to="localePath('/auth/register')">
            {{ $t('nav.register') }}
          </nuxt-link>
          <nuxt-link tag="b-nav-item" :to="localePath('/auth/login')">
            {{ $t('nav.login') }}
          </nuxt-link>
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
    <div id="usermanager-content">
      <div v-if="isRouterEnabled" id="router">
        <Nuxt />
      </div>
      <div v-else>
        {{ $t('disabled') }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DefaultLayout',
  data() {
    return {
      enabled: true
    }
  },
  computed: {
    isRouterEnabled() {
      return this.enabled
    },
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
