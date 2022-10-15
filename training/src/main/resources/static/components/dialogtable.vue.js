var dialogtable = Vue.component("d-table", {
      props : ["field","meta", "data"],
      data() {
         return {
            datas:  [],
            selectedItem : null,
            page : {page: 0, size:0, search: ""}
         }
      },methods : {
        isDataArray() {
            return Array.isArray(this.data[this.field.name]) ;
         },setSelectedItem(data) {
            this.selectedItem = data;
         },isSelected(item){
             return item == this.selectedItem;
         },itemSelected(item) {
            this.isDataArray();
             if (this.data != null) {
                 if (this.isDataArray()) {
                    if (!this.data[this.field.name].map(i => i.pk).includes(item.pk)) {
                      this.data[this.field.name].push(item);
                    }
                 } else {
                   this.data[this.field.name] = item;
                 }
                  var modalEl = document.getElementById(this.id);
                  var modal = bootstrap.Modal.getInstance(modalEl)
                  modal.hide();
              }
          }, async searchAction() {
              try {
                 let searchUrl =this.url ;
                 if (this.page.search) {
                    if (searchUrl.includes("filter")) {
                       searchUrl = searchUrl.concat("&search=")
                       .concat(this.page.search)
                       .concat("&page="+this.page.page);
                    } else {
                       searchUrl = searchUrl.concat("/?search=")
                        .concat(this.page.search)
                        .concat("&page="+this.page.page);;
                    }
                 }
                 this.datas.splice(0, this.datas.length);
                 let response = await axios.get(searchUrl);
                 for(i=0; i < response.data.items.length; i++) {
                   this.datas.push(response.data.items[i]);
                 }
                 this.page.size = response.data.totalPages;
                 //console.log("Search Action result  ::: "+JSON.stringify(response.data));
              } catch(error) {

              }
          }
      },computed : {
          columns() {
              return this.meta != null && this.meta.columns != null ? this.meta.columns : [];
          },
          id() { return "d-"+this.field.name ;},
          filters() { return Array.isArray(this.field.filters) ? this.field.filters:[] },
          url() {
                let rules = "";
                for (var index in this.filters) {
                     var rule = this.filters[index];
                     if (rules=="") {
                         rules= rules.concat(rule.field+"-"+rule.operator+"-"+rule.value)
                     } else {
                        rules= "&"+rules.concat(rule.field+"-"+rule.operator+"-"+rule.value)
                     }
                }
                let url = this.field.source;
                if (rules != "") {
                   url = url.concat("?filter=").concat(rules);
                }
                return url;
          }
      },async created(){
        try {
             let response = await axios.get(this.url);
             this.datas = response.data.items ;
             this.page.page = response.data.currentPage;
             this.page.size = response.data.totalPages;
         } catch(error) {
             this.$emit("notify-error", error);
         }
      },template: `<div>
                     <div class="title-bar title-bloc modal-header-background">
                           <v-search @search-action="searchAction" :page="page" ></v-search>
                      </div>
                      <div class="table-responsive">
                          <table class="table table-striped table-hover table-sm">
                              <thead class="table-header-theme">
                              <tr  class="table-header">
                                  <th scope="col" v-for="c of columns">{{c.label}}</th>
                              </tr>
                              </thead>
                              <tbody>
                                  <tr class="clickable-row" v-for="data of datas" :click="setSelectedItem(data)" :class="{rowSelected : isSelected(data)}">
                                      <td v-for="col of columns" v-on:dblclick="itemSelected(data)" >
                                           <span v-if="col.type == 'many-to-one' && data[col.name] != null">{{data[col.name].value}}</span>
                                           <span class="form-check form-switch" v-else-if="col.type == 'checkbox'">
                                            <input class="form-check-input" type="checkbox" v-model="data[col.name]" :checked="data[col.name]" disabled>
                                           </span>
                                           <span v-else>{{data[col.name]}}</span>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                      <div class="margin-left-auto modal-pagination"><v-pagination :page="page" @pagination-action="searchAction"></v-pagination></div>
                      </div>`
});