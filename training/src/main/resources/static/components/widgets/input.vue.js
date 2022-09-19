var input = Vue.component("t-text-field", {
        props: ["field", "data"],
        data() {
           return {
              value : this.data == null ? {} : this.data
           }
        },computed : {
           readonly() { return this.disabled == null ? false : this.disabled; },
           id() { return this.field.name ;},
           disabled() { return !this.field.editable || !this.field.updatable && this.data.pk >0 && this.data[this.field.name] != null ;},
           type() {
              return this.field.type;
           },
           isNotNull() { return this.field == null || this.field == undefined }
        },created() {
            //console.log("********************** "+JSON.stringify(this.field))
        },
        methods: {
           updateValue() {
               this.$emit('input', this.data);
           },async preview(event) {
              let files = event.target.files ;

              if (files != null && files.length >0 ) {

              }
              //console.log("list of selectd files : "+event.target.files);
           }
        },template: `<div>
                       <label :for="id" class="form-label">{{field.label}}</label>
                       <input :type="type" class="form-control form-control-sm" :readonly="readonly" @change="preview"
                                     :required="!field.nullable" v-model="data[field.name]"  :id="id" placeholder="">
                     </div>`
});