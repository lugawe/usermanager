<template>
  <div class="default-layout">
    <div v-if="enabled" class="usermanager">
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
            <b-nav-item-dropdown :text="$t('nav.user.user')" right>
              <div v-if="isLoggedIn">
                <nuxt-link tag="b-dropdown-item" :to="localePath('/main/users/me')">
                  {{ $t('nav.user.profile') }}
                </nuxt-link>
                <nuxt-link tag="b-dropdown-item" :to="localePath({ path: '/auth/login', query: { logout: 'true' } })">
                  {{ $t('nav.user.logout') }}
                </nuxt-link>
              </div>
              <div v-else>
                <nuxt-link tag="b-dropdown-item" :to="localePath('/auth/login')">
                  {{ $t('nav.user.login') }}
                </nuxt-link>
              </div>
            </b-nav-item-dropdown>
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
      <div class="content">
        <div v-if="isRouterEnabled" class="router">
          <Nuxt />
        </div>
        <div v-else>
          {{ $t('disabled') }}
        </div>
      </div>
    </div>
    <div v-else class="usermanager">
      <b-spinner />
    </div>
  </div>
</template>

<script>
import auth from '~/services/auth'

export default {
  name: 'DefaultLayout',
  data() {
    return {
      enabled: false
    }
  },
  computed: {
    isRouterEnabled() {
      return true
    },
    locales() {
      return this.$i18n.locales
    }
  },
  mounted() {
    this.checkLogin()
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
    },
    checkLogin() {
      auth
        .check(this.$axios)
        .then((response) => {
          this.$store.commit('auth/login', { user: response.data })
        })
        .catch((ex) => {
          this.$store.commit('auth/logout')
        })
        .finally(() => {
          this.enabled = true
        })
    }
  }
}
</script>

<style scoped>
.usermanager {
  background-color: white;
}
</style>
