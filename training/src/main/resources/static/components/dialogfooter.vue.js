var dialogfooter = Vue.component("d-footer", {
   props: ["type"],
   data() { return {
         i18n : {}
      }
   }, methods : {
     save() {
        this.$emit("d-save-event");
     },getMessage(key) {
        return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
     }
   },computed : {
       isViewMode() { return this.type == "view";}
   }, async created() {
      try {
           let response = await axios.get("/api/v1/i18n?keys=dcancel.btn,accept.btn");
           this.i18n = response.data;
       } catch(error) {
          console.log(error);
       }
   },
   template: ` <div class="modal-footer">
                  <button v-if="isViewMode" type="button" class="btn btn-primary btn-sm" @click="save()" >{{getMessage('accept.btn')}}</button>
                  <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">{{getMessage('dcancel.btn')}}</button>
               </div>`
});