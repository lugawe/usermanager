<template>
  <div class="component-quick-jumbotron">
    <b-jumbotron class="clickable" :header="header" :lead="lead">
      <slot />
    </b-jumbotron>
  </div>
</template>

<script>
export default {
  name: 'QuickJumbotron',
  props: {
    header: {
      type: String,
      required: true
    },
    lead: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped></style>
