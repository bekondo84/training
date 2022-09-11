var select = Vue.component("v-select", {
   props : ["field", "data"],
   data() {
      return {
          items : this.field !=null && this.field.selectItems != null ? this.field.selectItems : []
      }
   },computed : {

   },methods : {
       isSelected(item) {
           return item==null || item.value != this.data[this.field.name] ? false : true ;
       }
   }, created() {
   },template : `<div>
                     <label :for="id" class="form-label">{{field.label}}</label>
                     <select class="form-select form-select-sm" v-model="data[field.name]" >
                       <option ></option>
                       <option :selected="isSelected(item)" v-for="item of items" :value="item.value">{{item.name}}</option>
                     </select>
                 </div>`
});