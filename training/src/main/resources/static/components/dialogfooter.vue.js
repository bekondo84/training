var dialogfooter = Vue.component("d-footer", {
   props: ["type"],
   methods : {
     save() {
        this.$emit("d-save-event");
     }
   },computed : {
       isViewMode() { return this.type == "view";}
   },
   template: ` <div class="modal-footer">
                  <button v-if="isViewMode" type="button" class="btn btn-primary btn-sm" @click="save()" >accept.btn</button>
                  <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">cancel.btn</button>
               </div>`
});