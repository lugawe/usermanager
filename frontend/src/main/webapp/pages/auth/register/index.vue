<template>
  <div id="auth-register-index">
    <div class="m-4">
      <b-form class="form-register p-3" @submit="register" @reset="reset">
        <h2>{{ $t('auth.register.index.register') }}</h2>
        <hr>
        <!-- -->
        <b-input-group class="mb-2">
          <b-form-input
            id="input-username"
            v-model="username"
            :placeholder="$t('auth.register.index.username')"
            :readonly="loading"
            required
          />
          <b-tooltip v-if="!loading" target="input-username" triggers="hover">
            {{ $t('auth.register.index.enterUsername') }}
          </b-tooltip>
          <template v-if="!isUsernameValid" #append>
            <b-input-group-text id="input-username-invalid" class="unselectable">
              <strong class="text-danger">!</strong>
            </b-input-group-text>
            <b-tooltip target="input-username-invalid" triggers="hover">
              {{ $t('validation.invalidUsername') }}
            </b-tooltip>
          </template>
        </b-input-group>
        <!-- -->
        <b-input-group class="mb-2">
          <b-form-input
            id="input-email"
            v-model="email"
            :placeholder="$t('auth.register.index.email')"
            :readonly="loading"
            required
          />
          <b-tooltip v-if="!loading" target="input-email" triggers="hover">
            {{ $t('auth.register.index.enterEmail') }}
          </b-tooltip>
          <template v-if="!isEmailValid" #append>
            <b-input-group-text id="input-email-invalid" class="unselectable">
              <strong class="text-danger">!</strong>
            </b-input-group-text>
            <b-tooltip target="input-email-invalid" triggers="hover">
              {{ $t('validation.invalidEmail') }}
            </b-tooltip>
          </template>
        </b-input-group>
        <!-- -->
        <b-input-group class="mb-2">
          <b-form-input
            id="input-password"
            v-model="password"
            type="password"
            :placeholder="$t('auth.register.index.password')"
            :readonly="loading"
            required
          />
          <b-tooltip v-if="!loading" target="input-password" triggers="hover">
            {{ $t('auth.register.index.enterPassword') }}
          </b-tooltip>
          <template v-if="!isPasswordValid" #append>
            <b-input-group-text id="input-password-invalid" class="unselectable">
              <strong class="text-danger">!</strong>
            </b-input-group-text>
            <b-tooltip target="input-password-invalid" triggers="hover">
              {{ $t('validation.invalidPassword') }}
            </b-tooltip>
          </template>
        </b-input-group>
        <!-- -->
        <b-input-group class="mb-2">
          <b-form-input
            id="input-confirm-password"
            v-model="confirmPassword"
            type="password"
            :placeholder="$t('auth.register.index.confirmPassword')"
            :readonly="loading"
            required
          />
          <b-tooltip v-if="!loading" target="input-confirm-password" triggers="hover">
            {{ $t('auth.register.index.enterConfirmPassword') }}
          </b-tooltip>
          <template v-if="!isConfirmPasswordValid" #append>
            <b-input-group-text id="input-confirm-password-invalid" class="unselectable">
              <strong class="text-danger">!</strong>
            </b-input-group-text>
            <b-tooltip target="input-confirm-password-invalid" triggers="hover">
              {{ $t('validation.invalidConfirmPassword') }}
            </b-tooltip>
          </template>
        </b-input-group>
        <!-- -->
        <hr>
        <div class="bottom">
          <div v-if="loading">
            <b-button variant="primary" class="float-right" disabled>
              <b-spinner small />
            </b-button>
          </div>
          <div v-else>
            <b-button type="submit" variant="primary" class="float-right" :disabled="!isFormValid">
              {{ $t('auth.register.index.register') }}
            </b-button>
          </div>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import Validation from '~/validation/register'
import auth from '~/services/auth'

export default {
  name: 'Register',
  data() {
    return {
      loading: false,
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    }
  },
  computed: {
    isUsernameValid() {
      return Validation.isUsernameValid(this.username)
    },
    isEmailValid() {
      return Validation.isEmailValid(this.email)
    },
    isPasswordValid() {
      return Validation.isPasswordValid(this.password)
    },
    isConfirmPasswordValid() {
      return this.isPasswordValid && this.password === this.confirmPassword
    },
    isFormValid() {
      return this.isUsernameValid && this.isEmailValid && this.isConfirmPasswordValid
    }
  },
  mounted() {
    this.parseQueryParams()
  },
  methods: {
    register(e) {
      e.preventDefault()
      this.loading = true
      auth.register(this.$axios, this.username, this.email, this.password).finally(() => {
        this.loading = false
      })
    },
    reset(e) {
      e.preventDefault()
      this.username = ''
      this.email = ''
      this.password = ''
      this.confirmPassword = ''
    },
    parseQueryParams() {
      this.username = this.$route.query.username || this.username
      this.email = this.$route.query.email || this.email
    }
  }
}
</script>

<style scoped>
.form-register {
  max-width: 440px;
  margin: 0 auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  overflow: hidden;
}
.unselectable {
  user-select: none;
}
</style>
