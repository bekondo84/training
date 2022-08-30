var dialog = Vue.component("t-dialog", {
   props : ["field", "meta", "data", "type"],
   data() {
      return {

      }
   },methods :{
      save() {
      }
   },computed : {
        id() { return "d-"+this.field.name ;},
        title() {
             if (this.meta != null ) {
                return this.type!=null && this.type=='view' ? this.meta.formTitle : this.meta.listTitle
             }
             return "";
         }
   },template: `<!-- Modal -->
                <div class="modal fade" :id="id" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                  <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                       <div class="modal-header">
                            <h5 class="modal-title">{{title}}</h5>
                            <button type="button" class="btn-close btn-sm" data-bs-dismiss="modal" aria-label="Close"></button>
                       </div>
                       <div class="modal-body">
                        <d-table v-if="type=='list'"
                          :meta="meta"
                          :field="field"
                          :data="data"
                        ></d-table>
                        <d-form v-else
                            :meta="meta"
                            :field="field"
                            :data="data">
                        </d-form>
                      </div>
                      <div class="modal-footer" v-if="type =='view'">
                        <button type="button" class="btn btn-primary" @click="save()">accept.btn</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">cancel.btn</button>
                      </div>
                    </div>
                  </div>
                </div>`
});