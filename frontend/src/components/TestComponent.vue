<template>
  <button @click="loadData()">Load Data!</button>
  <h2>{{ message }}</h2>
</template>

<script>
const url = 'http://localhost:8080/codesnippets-1.0-SNAPSHOT/api';

export default {
  name: "TestComponent",
  props: {
    msg: String
  },
  data() {
    return {
      message: this.msg,
      ogMessage: this.msg,
    }
  },
  methods: {
    loadData() {
      this.axios.get(`${url}/system/ping`).then(response => {
        this.message = response.data.time
      });

      setTimeout(() => {
        this.message = this.ogMessage
      }, 1000)
    }
  }
}
</script>

<style scoped>
</style>