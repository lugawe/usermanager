<template>
  <div id="default-layout">
    <b-navbar toggleable="lg" type="dark" variant="primary">
      <b-navbar-brand to="/">
        {{ $t('usermanager') }}
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse" />
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto" />
      </b-collapse>
    </b-navbar>
    <div id="router">
      <Nuxt />
    </div>
  </div>
</template>

<script></script>

<style></style>
