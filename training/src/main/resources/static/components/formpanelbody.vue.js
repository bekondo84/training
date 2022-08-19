var formbody = Vue.component("f-body", {
    props : ["meta", "data"],
    data() {
       return {
          tab : null
       }
    },methods : {
        isInputField(field) {
              return field.type == "text" || field.type=="number" ;
         },
         isTextareaField(field) {
               return field.type == "textarea";
         },
         isCheckboxField(field) {
              return field.type =="checkbox";
         },
         isDateField(field) {
            return field.type =="date";
         }
    },created() {
       //console.log(" !!!!!!!!!!!!!!!!!!!!!! "+JSON.stringify(this.data));
    },template:  `<v-tabs v-model="tab">
                       <v-tab v-for="tab of meta.groups" :key="tab.name">{{tab.label}}</v-tab>
                       <v-tab-item  v-for="tab in meta.groups" :key="tab.name">
                           <v-container style="border-bottom:0">
                             <v-row style="margin-left:30px;" v-if="tab.fields.length > 1">
                                <v-col col="12" md="5" v-for="field in tab.fields">
                                    <v-text-field  v-if="isInputField(field)"
                                     :label="field.label"
                                     v-model="data[field.name]"
                                     :type="field.type"></v-text-field>
                                     <v-textarea v-if="isTextareaField(field)"
                                           :autocomplete="field.label"
                                           :label="field.label"
                                           v-model="data[field.name]"
                                         ></v-textarea>
                                     <v-checkbox v-if="isCheckboxField(field)"
                                           v-model="data[field.name]"
                                           :label="field.label"
                                         ></v-checkbox>
                                </v-col>
                             </v-row>
                             <v-row v-else>
                                  <v-col col="12" md="12" v-for="field in tab.fields">
                                     <v-text-field  v-if="isInputField(field)"
                                      :label="field.label"
                                      v-model="data[field.name]"
                                      :type="field.type"></v-text-field>
                                      <v-textarea v-if="isTextareaField(field)"
                                            :autocomplete="field.label"
                                            :label="field.label"
                                            v-model="data[field.name]"
                                          ></v-textarea>
                                 </v-col>
                             </v-row>
                           </v-container>
                       </v-tab-item>
                     </v-tabs>`
});