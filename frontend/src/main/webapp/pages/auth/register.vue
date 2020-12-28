<template>
  <div id="auth-register">
    <div class="m-4">
      <b-form class="form-register p-3" @submit="register" @reset="reset">
        <h2>{{ $t('auth.register.register') }}</h2>
        <hr>
        <!-- -->
        <b-form-input
          id="input-username"
          v-model="username"
          class="mb-2"
          :placeholder="$t('auth.register.username')"
          :readonly="loading"
          :state="isUsernameValid"
          required
        />
        <b-tooltip v-if="!loading" target="input-username" triggers="hover">
          {{ $t('auth.register.enterUsername') }}
        </b-tooltip>
        <!-- -->
        <b-form-input
          id="input-email"
          v-model="email"
          class="mb-2"
          :placeholder="$t('auth.register.email')"
          :readonly="loading"
          :state="isEmailValid"
          required
        />
        <b-tooltip v-if="!loading" target="input-email" triggers="hover">
          {{ $t('auth.register.enterEmail') }}
        </b-tooltip>
        <!-- -->
        <b-form-input
          id="input-password"
          v-model="password"
          class="mb-2"
          type="password"
          :placeholder="$t('auth.register.password')"
          :readonly="loading"
          :state="isPasswordValid"
          required
        />
        <b-tooltip v-if="!loading" target="input-password" triggers="hover">
          {{ $t('auth.register.enterPassword') }}
        </b-tooltip>
        <!-- -->
        <b-form-input
          id="input-confirm-password"
          v-model="confirmPassword"
          class="mb-2"
          type="password"
          :placeholder="$t('auth.register.confirmPassword')"
          :readonly="loading"
          :state="isConfirmPasswordValid"
          required
        />
        <b-tooltip v-if="!loading" target="input-confirm-password" triggers="hover">
          {{ $t('auth.register.enterConfirmPassword') }}
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
            <b-button type="submit" variant="primary" class="float-right" :disabled="!isFormValid">
              {{ $t('auth.register.register') }}
            </b-button>
          </div>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import Validation from '~/validation/register'

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
  methods: {
    register(e) {
      e.preventDefault()
    },
    reset(e) {
      e.preventDefault()
      this.username = ''
      this.email = ''
      this.password = ''
      this.confirmPassword = ''
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
</style>
