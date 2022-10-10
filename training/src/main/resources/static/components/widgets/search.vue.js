var search = Vue.component("v-search", {

   data() {
      return {
        text : null
      }
   }, methods: {
         searchAction() {
            this.$emit("search-action", this.text);
         }
   }, computed: {

   },template:`<div class="input-group margin-left-auto width-350">
                           <input type="search" class="form-control form-control-sm rounded" v-model="text" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                           <button type="button" class="btn btn-outline-secondary btn-sm" @click="searchAction"><img src="../../images/search.png" class="rounded" width="17px"></button>
                         </div>`
});