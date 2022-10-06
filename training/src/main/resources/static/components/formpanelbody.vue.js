var formbody = Vue.component("f-body", {
    props : ["meta", "data"],
    data() {
       return {
          tab : null ,
          context : new Object(),
          value : "",
          values : ["Monda","Wenesda","Frida"]
       }
    },methods : {
        isInputField(field) {
              return field.type == "text" || field.type=="number" || field.type=="datetime-local"
                 || field.type =="date" || field.type=="time"
                 || field.type=="email" || field.type=="password";
         },isFileInputField(field) {
            return field.type=="file" ;
         },isTextareaField(field) {
               return field.type == "textarea";
         },
         isCheckboxField(field) {
              return field.type =="checkbox";
         },
         isDateField(field) {
            return field.type =="date";
         },isDisabled(field) {
             return !field.editable || !field.updatable && this.data.pk >0 && this.data[field.name] != null ;
         },isSelect(field) {
            return field.type == "select";
         },isManyToOneField(field) {
            return field.type =="many-to-one";
         },isOneToManyField(field) {
              return field.type =="one-to-many";
           },isManyToManyField(field) {
                          return field.type =="many-to-many";
                       },async initField(field) {
           try{
                let response = await axios.get(field.source);
                this.context[field.name]  = response.data ;
            } catch( error ) {
               console.log(error);
            }
         }
    },created() {
       //this.context = new Object();
       this.context['plugin'] = [];

    },template:  `<v-tabs v-model="tab">
                       <v-tab v-for="tab of meta.groups" :key="tab.name">{{tab.label}}</v-tab>
                       <v-tab-item  v-for="tab in meta.groups" :key="tab.name">
                           <v-container style="border-bottom:0">
                             <v-row style="margin-left:30px;" v-if="tab.fields.length > 1">
                                <v-col col="12" md="5" v-for="field in tab.fields">
                                   <t-text-field  v-if="isInputField(field)"
                                     :field="field"
                                     :data="data"></t-text-field>
                                   <t-file-field  v-if="isFileInputField(field)"
                                      :field="field"
                                      :data="data"></t-file-field>
                                     <v-textarea v-if="isTextareaField(field)"
                                           :autocomplete="field.label"
                                           :label="field.label"
                                           v-model="data[field.name]"
                                           :disabled="isDisabled(field)"></v-textarea>
                                     <t-checkbox v-if="isCheckboxField(field)"
                                           :data="data"
                                           :field="field"
                                           :disabled="isDisabled(field)"></t-checkbox>
                                     <t-manytoone v-if="isManyToOneField(field)"
                                         v-model="data[field.name]"
                                         :field="field"
                                         :data="data"
                                         :disabled="isDisabled(field)"></t-manytoone>
                                      <v-onetomany v-if="isOneToManyField(field)"
                                          :field="field"
                                          :data="data"
                                          :disabled="isDisabled(field)"></v-onetomany>
                                      <v-manytomany v-if="isManyToManyField(field)"
                                          :field="field"
                                          :data="data"
                                          :disabled="isDisabled(field)"></v-manytomany>
                                      <v-select v-if="isSelect(field)"
                                              :field="field"
                                              :data="data"
                                              :disabled="isDisabled(field)"></v-select>
                                </v-col>
                             </v-row>
                             <v-row v-else>
                                  <v-col col="12" md="12" v-for="field in tab.fields">
                                     <v-text-field  v-if="isInputField(field)"
                                      :label="field.label"
                                      v-model="data[field.name]"
                                      :type="field.type"
                                      :disabled="isDisabled(field)"></v-text-field>
                                  <t-file-field  v-if="isFileInputField(field)"
                                      :field="field"
                                      :data="data"></t-file-field>
                                      <v-textarea v-if="isTextareaField(field)"
                                            :autocomplete="field.label"
                                            :label="field.label"
                                            v-model="data[field.name]"
                                            :disabled="isDisabled(field)"></v-textarea>
                                      <v-onetomany v-if="isOneToManyField(field)"
                                             :field="field"
                                             :data="data"
                                             :disabled="isDisabled(field)"></v-onetomany>
                                      <v-manytomany v-if="isManyToManyField(field)"
                                            :field="field"
                                            :data="data"
                                            :disabled="isDisabled(field)"></v-manytomany>
                                 </v-col>
                             </v-row>
                           </v-container>
                       </v-tab-item>
                     </v-tabs>`
});