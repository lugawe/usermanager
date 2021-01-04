<template>
  <div v-if="isLoggedIn" id="main-users-index" class="p-4">
    <b-skeleton-wrapper :loading="loading">
      <template #loading>
        <b-skeleton-table
          :columns="tableColumnsLength"
          :rows="perPage"
          :table-props="{ bordered: true, outlined: true, striped: true }"
        />
      </template>
      <b-table
        hover
        bordered
        outlined
        striped
        show-empty
        :busy="loading"
        :fields="tableColumns"
        :items="tableRows"
        :current-page="currentPage"
      >
        <template #cell(actions)="row">
          {{ row.item.id }}
        </template>
        <template #table-caption>
          {{ status }}
        </template>
      </b-table>
    </b-skeleton-wrapper>
    <b-pagination
      v-model="currentPage"
      first-number
      last-number
      align="right"
      :disabled="loading"
      :total-rows="tableRowsLength"
      :per-page="perPage"
    >
      <template v-if="loading" #ellipsis-text>
        <b-spinner small variant="primary" />
      </template>
    </b-pagination>
  </div>
</template>

<script>
export default {
  name: 'Users',
  data() {
    return {
      loading: true,
      currentPage: 1,
      perPage: 10
    }
  },
  computed: {
    tableColumns() {
      return [
        {
          key: 'id',
          label: this.$t('main.users.index.id'),
          sortable: true
        },
        {
          key: 'firstName',
          label: this.$t('main.users.index.firstName'),
          sortable: true
        },
        {
          key: 'lastName',
          label: this.$t('main.users.index.lastName'),
          sortable: true
        },
        {
          key: 'username',
          label: this.$t('main.users.index.username'),
          sortable: true
        },
        {
          key: 'email',
          label: this.$t('main.users.index.email'),
          sortable: true
        },
        {
          key: 'actions',
          label: this.$t('main.users.index.actions')
        }
      ]
    },
    tableColumnsLength() {
      return this.tableColumns.length
    },
    tableRows() {
      return []
    },
    tableRowsLength() {
      return this.tableRows.length
    },
    status() {
      return 'test'
    }
  },
  watch: {
    currentPage(val) {}
  }
}
</script>

<style scoped></style>
