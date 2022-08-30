var dialogform = Vue.component("d-form", {
    props :["field", "meta", "data"],
    data() {
        return {
            tab : null ,
            context : new Object()
        }
    }, computed : {
       groups() { return this.meta != null ? this.meta.groups : []}
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
        },isDisabled(field) {
            return !field.editable || !field.updatable && this.data.pk >0 && this.data[field.name] != null ;
        },isManyToOneField(field) {
           return field.type =="many-to-one";
        },isOneToManyField(field) {
             return field.type =="one-to-many";
          },async initField(field) {
              try{
                   let response = await axios.get(field.source);
                   this.context[field.name]  = response.data ;
               } catch( error ) {
                  console.log(error);
               }
         }
    }, template: `<v-tabs>
                      <v-tab v-for="grp of groups" :key="grp.name">{{grp.label}}</v-tab>
                      <v-tab-item  v-for="grp in groups" :key="grp.name">
                         <v-container style="border-bottom:0">
                          <v-row style="margin-left:30px;" v-if="grp.fields.length > 1">
                               <v-col col="12" md="5" v-for="field in grp.fields">
                                  <t-text-field  v-if="isInputField(field)"
                                       :field="field"
                                       :data="data"></t-text-field>
                                  <t-checkbox v-if="isCheckboxField(field)"
                                     :data="data"
                                     :field="field">
                                  </t-checkbox>
                               </v-col>
                          </v-row>
                          <v-row v-else>
                             <v-col col="12" md="12" v-for="field in grp.fields">
                                  <t-text-field  v-if="isInputField(field)"
                                         :field="field"
                                         :data="data"></t-text-field>
                                 <t-checkbox v-if="isCheckboxField(field)"
                                    :data="data"
                                    :field="field">
                                 </t-checkbox>
                             </v-col>
                          </v-row>
                        </v-container>
                      </v-tab-item>
                   </v-tabs>`
});