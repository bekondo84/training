var myDialog = Vue.component("myDialog", {
    props: ["data"],
    data() {
       return {
          meta : null,
          i18n: {}
       }
    }, methods : {
          heading(group) { return "heading".concat(group.pk);},
          aria(group) { return "flush-heading".concat(group.pk);},
          collapse(group) { return "collapse".concat(group.pk);},
          dataBs(group) { return "#collapse"+group.pk;},
          register(group) {
             this.$emit("register-action", group);
          },unRegister(group) {
              this.$emit("unregister-action", group);
          }, getMessage(key) {
              return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
          }
    }, computed : {
          dialogId() { return "my-dialog";},
          dialogTitleId() { return "my-dialoglabel";},
          groups() { return this.data != null && this.data.groups != null ? this.data.groups : []},
          title() {
              if (this.meta == null || this.data == null || this.data.training ==null) {
                 return "";
              }
              return this.meta.listTitle.concat(" : "+this.data.training.name);
          }
    }, async created() {
          try {
             let response = await axios.get("/api/v1/meta/cm.pak.training.beans.training.MyLearningGroupData");
             this.meta = response.data ;
             response = await axios.get("/api/v1/i18n?keys=dcancel.btn,register.btn,unregister.btn,day.label,startAt.label,endAt.label,subject.label");
             this.i18n = response.data;
          } catch (error) {
             console.log(error);
          }
    }, template : `<div class="modal fade" :id="dialogId" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                     <div class="modal-dialog modal-lg modal-dialog-centered">
                       <div class="modal-content">
                         <div class="modal-header">
                           <h6 class="modal-title" :id="dialogTitleId">{{title}}</h6>
                           <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                         </div>
                         <div class="modal-body">
                           <div class="accordion" id="accordionGroups">
                             <div class="accordion-item" v-for="group in groups">
                               <h2 class="accordion-header" :id="group.pk">
                                 <button class="accordion-button btn-sm" type="button" data-bs-toggle="collapse" :data-bs-target="dataBs(group)"
                                         aria-expanded="true" :aria-controls="collapse(group)">
                                   {{group.value}}
                                 </button>
                               </h2>
                               <div :id="collapse(group)" class="accordion-collapse collapse show" :aria-labelledby="heading(group)" data-bs-parent="#accordionGroups">
                                 <div class="accordion-body">
                                     <div class="title-bar">
                                        <nav class="nav">
                                          <a class="btn btn-success btn-sm" aria-current="page" href="#" @click.self="register(group)">{{getMessage('register.btn')}}</a>                                        </nav>
                                     </div>
                                     <div class="table-responsive">
                                        <table class="table table-striped table-hover table-sm">
                                          <thead>
                                            <tr>
                                              <th scope="col">{{getMessage('day.label')}}</th>
                                              <th scope="col">{{getMessage('startAt.label')}}</th>
                                              <th scope="col">{{getMessage('endAt.label')}}</th>
                                              <th scope="col">{{getMessage('subject.label')}}</th>
                                            </tr>
                                          </thead>
                                          <tbody>
                                            <tr v-for="sheet in group.timesheet">
                                              <th scope="row">{{sheet.day}}</th>
                                              <td>{{sheet.startAt}}</td>
                                              <td>{{sheet.endAt}}</td>
                                              <td>{{sheet.subject}}</td>
                                            </tr>
                                          </tbody>
                                        </table>
                                     </div>
                                 </div>
                               </div>
                             </div>
                           </div>
                         </div>
                         <div class="modal-footer">
                           <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">{{getMessage('dcancel.btn')}}</button>
                         </div>
                       </div>
                     </div>
                   </div>`
});