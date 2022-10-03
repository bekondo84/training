var manytoone = Vue.component("t-manytoone", {
    props: ["field", "data", "id", "disabled"],
    data() {
       return {
          meta: null
       }
    },computed : {
        readonly() { return this.disabled == null ? false : this.disabled; },
        fieldname() { return this.field != null ? this.field.name : null ;},
        keyValue() {
             if (this.meta != null && this.field != null && this.data != null) {
                 if (this.data[this.field.name] != null) {
                    return this.data[this.field.name][this.meta.searchKey];
                 }
             }
             return "";
         },value() {
            if (this.meta != null && this.field != null && this.data != null) {
                 if (this.data[this.field.name] != null) {
                    return this.data[this.field.name].value;
                 }
             }
             return "";
         }
     }, methods: {
         async onFocusLost(event) {
              let key = this.meta.searchKey ;
              let value = event.target.value;
              let target = this.field.metadata ;
              let fieldname = this.field.name ;
              try {
                let response = await axios.get("/api/v1/search/".concat(key)
                                   .concat("/").concat(value).concat("/").concat(target));
                 this.data[fieldname] = response.data ;
              }catch (error) {
                 console.log(error);
              }
         }
     },async created() {
        //console.log("--------------- : created :: "+JSON.stringify(this.field.metadata))
        try {
           let response = await axios.get("/api/v1/meta/".concat(this.field.metadata));
           this.meta = response.data;
         }catch (error) {
             console.log(error);
         }
     },template: `<div>
                          <label for="key" class="form-label field">{{field.label}}</label>
                          <div class="manytoone-box">
                             <input type="text"
                                   class="form-control form-control-sm"
                                   @blur="onFocusLost"
                                   style="width: 80px;"
                                   :id="id"
                                   :value="keyValue"
                                   :readonly="disabled"
                                   placeholder="">
                             <t-btn-select :field="field"
                                           :meta="meta"
                                           :data="data"
                                           :disabled="disabled"></t-btn-select>
                             <input type="text" class="form-control form-control-sm" :value="value" readonly>
                          </div>
                      </div>`
});