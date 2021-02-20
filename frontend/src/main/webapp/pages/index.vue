<template>
  <div id="usermanager-index" class="p-4">
    <div v-if="isLoggedIn">
      <b-row>
        <b-col>
          <quick-jumbotron />
        </b-col>
        <b-col>
          <quick-jumbotron />
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <quick-jumbotron />
        </b-col>
        <b-col>
          <quick-jumbotron />
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import QuickJumbotron from '~/components/QuickJumbotron.vue'

export default {
  name: 'Index',
  components: {
    QuickJumbotron
  },
  mounted() {
    if (!this.isLoggedIn) {
      this.gotoLogin()
    }
  }
}
</script>

<style></style>
