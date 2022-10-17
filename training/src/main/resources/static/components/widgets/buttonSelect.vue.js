var btn = Vue.component("t-btn-select", {
    props :["field", "meta", "data", "disabled"],
    data() {
       return {
         dialog : "t-dialog",
         hidden : true,
         type: null
       }
    },computed : {
         id() { return "#d-".concat(this.field.name)},
         id() { return "d-".concat(this.field.name) ;}
    },methods : {
       showDialog() {
           try {
               this.dialog = "t-dialog";
               this.type="list";
               this.keyValue = new Date().getTime();
               var modal = new bootstrap.Modal(document.getElementById(this.id), {
                 keyboard: false
               });
               modal.show();
           } catch(error) {}
        }
    },created(){
       //console.log("------------------------------------- : "+JSON.stringify(this.field))
    },template: `<!-- Button trigger modal -->
                 <div>
                 <button type="button" class="btn btn-sm" :disabled="disabled" @click="showDialog">
                    <img src="../../images/search.png" class="rounded" width="17px">
                 </button>
                 <component v-bind:is="dialog"  v-if="hidden"
                      :key="field.name"
                      :field="field"
                      :meta="meta"
                      :data="data"
                      :type="type"></component></div>`
});