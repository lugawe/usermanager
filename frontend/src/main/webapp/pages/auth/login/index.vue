<template>
  <div id="auth-login-index">
    <div class="m-4">
      <b-form class="form-login p-3" @submit="login" @reset="reset">
        <h2>{{ $t('auth.login.login') }}</h2>
        <hr>
        <!-- -->
        <b-form-input
          id="input-username"
          v-model="username"
          class="mb-2"
          :placeholder="$t('auth.login.username')"
          :readonly="loading"
          required
        />
        <b-tooltip v-if="!loading" target="input-username" triggers="hover">
          {{ $t('auth.login.enterUsername') }}
        </b-tooltip>
        <!-- -->
        <b-form-input
          id="input-password"
          v-model="password"
          class="mb-2"
          type="password"
          :placeholder="$t('auth.login.password')"
          :readonly="loading"
          required
        />
        <b-tooltip v-if="!loading" target="input-password" triggers="hover">
          {{ $t('auth.login.enterPassword') }}
        </b-tooltip>
        <!-- -->
        <hr>
        <div class="bottom">
          <div v-if="loading">
            <b-button variant="primary" class="float-right" disabled>
              <b-spinner small />
            </b-button>
          </div>
          <div v-else>
            <b-link>{{ $t('auth.login.canNotLogin') }}</b-link>
            <b-button type="submit" variant="primary" class="float-right">
              {{ $t('auth.login.login') }}
            </b-button>
          </div>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      loading: false,
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
    },
    reset(e) {
      e.preventDefault()
      this.username = ''
      this.password = ''
      this.token = ''
    },
    parseQueryParams() {
      this.username = this.$route.query.username || ''
      this.password = this.$route.query.password || ''
      this.token = this.$route.query.token || ''
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
