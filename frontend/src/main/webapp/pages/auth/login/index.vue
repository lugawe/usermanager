<template>
  <div id="auth-login-index">
    <div class="m-4">
      <b-form class="form-login p-3" @submit="login" @reset="reset">
        <h2>{{ $t('auth.login.index.login') }}</h2>
        <hr>
        <!-- -->
        <b-input-group class="mb-2">
          <b-form-input
            id="input-username"
            v-model="username"
            :placeholder="$t('auth.login.index.username')"
            :readonly="loading"
            required
          />
          <b-tooltip v-if="!loading" target="input-username" triggers="hover">
            {{ $t('auth.login.index.enterUsername') }}
          </b-tooltip>
        </b-input-group>
        <!-- -->
        <b-input-group class="mb-2">
          <b-form-input
            id="input-password"
            v-model="password"
            type="password"
            :placeholder="$t('auth.login.index.password')"
            :readonly="loading"
            required
          />
          <b-tooltip v-if="!loading" target="input-password" triggers="hover">
            {{ $t('auth.login.index.enterPassword') }}
          </b-tooltip>
        </b-input-group>
        <!-- -->
        <hr>
        <div class="bottom">
          <b-alert :show="error" variant="danger">
            {{ $t(errorText) }}
          </b-alert>
          <div v-if="loading">
            <b-button variant="primary" class="float-right" disabled>
              <b-spinner small />
            </b-button>
          </div>
          <div v-else>
            <b-link>{{ $t('auth.login.index.canNotLogin') }}</b-link>
            <b-button type="submit" variant="primary" class="float-right">
              {{ $t('auth.login.index.login') }}
            </b-button>
          </div>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import auth from '~/services/auth'

export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      error: false,
      errorText: '',
      username: '',
      password: '',
      token: ''
    }
  },
  mounted() {
    this.parseQueryParams()
  },
  methods: {
    login(e) {
      e.preventDefault()
      this.loading = true
      this.error = false
      this.errorText = ''
      auth
        .login(this.$axios, this.username, this.password)
        .catch((ex) => {
          if (ex.response.status === 401) {
            this.errorText = 'auth.login.index.invalidCredentials'
          } else {
            this.errorText = 'auth.login.index.errorOccurred'
          }
          this.error = true
        })
        .finally(() => {
          this.loading = false
        })
    },
    reset(e) {
      e.preventDefault()
      this.username = ''
      this.password = ''
      this.token = ''
    },
    parseQueryParams() {
      if (this.$route.query.logout === 'true') {
        this.$store.commit('auth/logout')
      }
      this.username = this.$route.query.username || this.username
      this.password = this.$route.query.password || this.password
      this.token = this.$route.query.token || this.token
    }
  }
}
</script>

<style scoped>
.form-login {
  max-width: 440px;
  margin: 0 auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  overflow: hidden;
}
</style>
