var listtable = Vue.component("l-table", {
      props : ["data", "meta"],
      data() {
         return {
            datas: this.data
         }
      },methods : {
          selectAll() {
              if (this.datas != null) {
                  for (var i=0 ; i < this.datas.length; i++) {
                      this.datas[i].selected = !this.datas[i].selected ;
                  }
              }
          },itemSelected(item) {
              this.$emit("item-selected", {"item" :item, "viewMode":"view"});
          }
      },computed : {
          columns() {
              return this.meta != null && this.meta.columns != null ? this.meta.columns : [];
          }
      },template: ` <div class="table-responsive">
                              <table class="table table-striped table-hover table-sm">
                                  <thead>
                                  <tr>
                                      <th scope="col">
                                          <input type="checkbox"  @change="selectAll()">
                                      </th>
                                      <th scope="col" v-for="c of columns">{{c.label}}</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                      <tr class="clickable-row" v-for="data of datas">
                                          <th scope="row">
                                              <input type="checkbox" v-model="data.selected">
                                          </th>
                                          <td v-for="col of columns" v-on:dblclick="itemSelected(data)">
                                              <span >{{data[col.name]}}</span>
                                          </td>
                                      </tr>
                                  </tbody>
                              </table>
                          </div>`
});