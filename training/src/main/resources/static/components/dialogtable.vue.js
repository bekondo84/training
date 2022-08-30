var dialogtable = Vue.component("d-table", {
      props : ["field","meta", "data"],
      data() {
         return {
            datas:  []
         }
      },methods : {
         itemSelected(item) {
              if (this.data != null) {
                  this.data[this.field.name] = item;
                  var modalEl = document.getElementById(this.id);
                  var modal = bootstrap.Modal.getInstance(modalEl)
                  modal.hide();
              }
          }
      },computed : {
          columns() {
              return this.meta != null && this.meta.columns != null ? this.meta.columns : [];
          },
          id() { return "d-"+this.field.name ;}
      },async created(){
        try {
             let response = await axios.get(this.field.source);
             this.datas = response.data ;
         } catch(error) {
             console.log(error);
         }
      },template: `<div style="border: solid 1px green; ">
                     <div  style="border: solid 1px yellow; ">Tool bar header</div>
                     <div class="table-responsive">
                              <table class="table table-striped table-hover table-sm">
                                  <thead>
                                  <tr>
                                      <th scope="col" v-for="c of columns">{{c.label}}</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                      <tr class="clickable-row" v-for="data of datas">
                                          <td v-for="col of columns" v-on:dblclick="itemSelected(data)">
                                              <span v-if="col.type == 'many-to-one' && data[col.name] != null">{{data[col.name].value}}</span>
                                              <span v-else>{{data[col.name]}}</span>
                                          </td>
                                      </tr>
                                  </tbody>
                              </table>
                          </div>
                      </div>`
});