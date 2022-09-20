var dialog = Vue.component("t-dialog", {
   props : ["field", "meta", "data", "type"],
   data() {
      return {
          errors : []
      }
   },computed : {
        source(){ return this.field.source ;},
        id() { return "d-"+this.field.name ;},
        title() {
             if (this.meta != null ) {
                return this.type!=null && this.type=='view' ? this.meta.formTitle : this.meta.listTitle
             }
             return "";
         }
   }, methods: {
       save() {
           this.validate();

           if (this.errors.length > 0) {
               alert("Error has detected");
           } else {
              var modal = new bootstrap.Modal(document.getElementById(this.id), {keyboard: false});
              modal.hide();
              this.$emit('dialog-save', this.data);
           }
       },validate() {
           if (this.meta != null) {
               for (var i=0; i < this.meta.groups.length; i++) {
                  let fields = this.meta.groups[i].fields;
                  if (fields != null) {
                      for (var j=0; j<fields.length; j++) {
                          this.validateItem(fields[j]);
                      }
                  }
               }
           }
       },isEmpty(field) {
           return this.data==null || this.data[field.name] == null;
       }, validateItem(field) {
           if (!field.nullable && this.isEmpty(field)) {
              this.errors.push(field);
           }
       }
   }, created() {
        this.errors = [];
   },template: `<div class="modal fade" :id="id" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                  <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                       <div class="modal-header">
                            <h5 class="modal-title title"">{{title}}</h5>
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
                            :data="data"
                            :type="type">
                        </d-form>
                      </div>
                      <d-footer :type="type"
                                 @d-save-event="save"></d-footer>
                    </div>
                  </div>
                </div>`
});