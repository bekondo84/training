var search = Vue.component("v-search", {

   data() {
      return {
        text : "",
         i18n : {}
      }
   }, methods: {
         searchAction() {
            this.$emit("search-action", this.text);
         }, getMessage(key) {
           return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
         }
   }, computed: {

   }, async created() {
      try {
           let response = await axios.get("/api/v1/i18n?keys=search.label");
           this.i18n = response.data;
       } catch(error) {
          console.log(error);
       }
   },template:`<div class="input-group margin-left-auto width-350">
                           <input type="search" class="form-control form-control-sm rounded" v-model="text" :placeholder="getMessage('search.label')" aria-label="Search" aria-describedby="search-addon" />
                           <button type="button" class="btn btn-outline-secondary btn-sm" @click="searchAction"><img src="../../images/search.png" class="rounded" width="17px"></button>
                         </div>`
});