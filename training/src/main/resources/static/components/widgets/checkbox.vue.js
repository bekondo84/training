var checkbox = Vue.component("t-checkbox", {
  props : ["field", "data"],
  data() {
     return {
        value : this.data == null ? {} : this.data
     }
  },computed : {
    id() { return this.field.name},
    readonly() { return !this.field.editable || !this.field.updatable && this.data.pk >0 && this.data[this.field.name] != null ;}
  },methods : {

  },created(){
     //console.log("################################################# : "+JSON.stringify(this.data))
  },template: `<div class="form-check form-switch">
                 <input class="form-check-input" type="checkbox" role="switch" :id="field.name"
                 v-model="value[field.name]" :readonly="readonly">
                 <label class="form-check-label field" :for="field.name">{{field.label}}</label>
               </div>`
});