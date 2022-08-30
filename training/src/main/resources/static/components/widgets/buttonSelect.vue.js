var btn = Vue.component("t-btn-select", {
    props :["field", "meta", "data", "disabled"],
    data() {
       return {

       }
    },computed : {
         id() { return "#d-".concat(this.field.name)}
    },methods : {

    },created(){
       //console.log("------------------------------------- : "+JSON.stringify(this.field))
    },template: `<!-- Button trigger modal -->
                 <div>
                 <button type="button" class="btn btn-primary btn-sm" :disabled="disabled" data-bs-toggle="modal" :data-bs-target="id">
                    ...
                 </button>
                 <t-dialog
                      :field="field"
                      :meta="meta"
                      :data="data"
                      type="list"></t-dialog></div>`
});