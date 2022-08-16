var menu = Vue.component("v-menus-bar", {
     props: ["menus"],
     created() {
       //console.log(JSON.stringify(this.menus));
     },methods: {
         onMenuSelected(menu) {
            this.$emit("menu-change", menu);
            console.log("*** Menu change : "+menu);
         }
     },template: `<section class="menu">
                      <ul>
                          <li v-for="menu of menus">
                              <a href="#" v-if="menu.children.length  == 0"  @click="onMenuSelected(menu)">{{menu.label}}</a>
                              <span v-if="menu.children.length  > 0">{{menu.label}}</span>
                              <ul v-if="menu.children.length  > 0">
                                  <li v-for="submenu of menu.children">
                                      <a href="#" v-if="submenu.children.length == 0"   @click="onMenuSelected(submenu)">{{submenu.label}}</a>
                                      <span v-if="submenu.children.length > 0">{{submenu.label}}</span>
                                      <ul v-if="submenu.children.length  > 0">
                                          <li v-for="menu02 of submenu.children">
                                              <a href="#"   @click="onMenuSelected(menu02)">{{submenu.label}}</a>
                                          </li>
                                      </ul>
                                  </li>
                              </ul>
                          </li>
                      </ul>
                  </section>`
});