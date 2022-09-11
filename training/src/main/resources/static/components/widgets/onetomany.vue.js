var onetomany = Vue.component("v-onetomany", {
     props: ["field", "data"],
     data() {
        return {
           meta : null,
           instance : null,
           dialog : "t-dialog",
           keyValue: 12
        }
     },computed : {
        columns() { return this.meta != null ? this.meta.columns : []},
        datas() {  return this.data == null ? [] : this.data[this.field.name]},
        id() { return "#d-".concat(this.field.name)},
        editable() { return this.field.editable } ,
        deletable() { return this.field.deletable },
        updatable() { return this.field.updatable;}
     },methods : {
        fieldValue(item, col) {
          if (item[col.name] != null && typeof item[col.name] == 'object') {
             return item[col.name].value ;
          }
          return item[col.name];
        }, async add() {
             try {
                let response = await axios.get("/api/v1/instance/".concat(this.field.metadata));
                this.instance = response.data ;
                this.dialog = "t-dialog";
                this.keyValue = new Date().getTime();
             } catch (error) {
                 console.log(error);
              }
         },itemSelected(item) {
            this.instance = item ;
         },update() {
            if (this.instance != null) {
              this.dialog = "t-dialog";
              this.keyValue = new Date().getTime();
            } else {
               alert("Veuillez selectionner une ligne");
            }
         }
     },async created() {
       try {
         let response = await axios.get("/api/v1/meta/".concat(this.field.metadata));
         this.meta = response.data;
       }catch (error) {
           console.log(error);
       }
    },watch : {
        data:  function(newVal, oldVal) {
            //console.log(JSON.stringify(newVal)+" : ********************************* : "+JSON.stringify(oldVal))
        }
    },template : `<div>
                        <div>
                           <nav class="nav">
                             <a class="nav-link data-button" aria-current="page" href="#"
                                 data-bs-toggle="modal" :data-bs-target="id"
                                 @click="add()" v-if="editable">
                                <img src="../../images/add.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#"
                                 data-bs-toggle="modal" :data-bs-target="id"
                                 @click="update()" v-if="updatable">
                                 <img src="../../images/upda.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#">
                                <img src="../../images/view.gif" class="rounded">
                             </a>
                             <a class="nav-link data-button" href="#" v-if="deletable">
                                <img src="../../images/dele.gif" class="rounded">
                             </a>
                           </nav>
                           <component v-bind:is="dialog"
                               :field="field"
                               :meta="meta"
                               :data="instance"
                               :key="keyValue"
                               type="view"></component>
                        </div>
                        <div class="table-responsive">
                                 <table class="table table-striped table-hover table-sm">
                                     <thead>
                                     <tr>
                                         <th scope="col" v-for="c of columns">{{c.label}}</th>
                                     </tr>
                                     </thead>
                                     <tbody>
                                         <tr class="clickable-row" v-for="data of datas">
                                             <td v-for="col of columns" v-on:click="itemSelected(data)">
                                                 <span  class="form-check form-switch" v-if="col.type == 'checkbox'">
                                                     <input class="form-check-input" type="checkbox" v-model="data[col.name]" :checked="data[col.name]">
                                                 </span>
                                                 <span v-else>{{fieldValue(data, col)}}</span>
                                             </td>
                                         </tr>
                                     </tbody>
                                 </table>
                             </div>
                      </div>`
});