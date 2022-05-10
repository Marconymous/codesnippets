<template>
  <button @click="loadData">Load Data!</button>
  <h2>{{ message }}</h2>
</template>

<script>
const url = 'http://localhost:8080/codesnippets-1.0-SNAPSHOT/api';

export default {
  name: "TestComponent",
  data() {
    return {
      message: "Hello Vue!"
    }
  },
  methods: {
    loadData() {
      this.axios.get(`${url}/system/ping`).then(response => {
        this.message = response.data.time
      });
    }
  }
}
</script>

<style scoped>

</style>