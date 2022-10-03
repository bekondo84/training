var menu = Vue.component("v-menus-bar", {
     props: ["menus"],
     data() {
        return {
           current : null
        }
     },created() {
       //console.log(JSON.stringify(this.menus));
     },methods: {
         onMenuSelected(menu) {
            this.current = menu;
            this.$emit("menu-change", menu);
         }, isMenu(menu) {
             return menu.children.length  > 0 ;
         }, isMenuItem(menu) {
           return menu.children.length  == 0
         }, show(menu) {
             return this.isMenu(menu) && menu.show ;
         }, showSubMenu(menu) {
             menu.show = !menu.show ;
             var elt = document.getElementById(menu.name);
             if (elt == null) {
               return;
             }
             if (menu.show) {
                elt.classList.remove("fa-caret-down");
                elt.classList.add("fa-caret-up");
             }else {
                elt.classList.remove("fa-caret-up");
                elt.classList.add("fa-caret-down");
             }
         }, isSelected(menu) {
             return this.current!=null && this.current.name == menu.name;
         }
     }, mounted() {

     },template: `<section class="v-menu-bar title-bloc">
                      <div>
                         <img src="./images/logo1.jpg" width="250" height="143">
                      </div>
                      <nav class="animated bounceInDown" style="overflow: auto;margin-left: 9px;">
                        <div>
                          <ul class="menus">
                              <li v-for="menu of menus" :class="{menu: isMenu(menu)}">
                                  <a href="#" v-if="isMenuItem(menu)"  @click="onMenuSelected(menu)"  :class="{currentMenu : isSelected(menu)}">{{menu.label}}</a>
                                  <a href="#" v-if="isMenu(menu)" @click="showSubMenu(menu)">{{menu.label}}<div  class="fa fa-caret-down right" :id="menu.name"></div></a>
                                  <ul  v-if="show(menu)">
                                      <li v-for="submenu of menu.children"  :class="{menu: isMenu(submenu)}">
                                          <a href="#" v-if="isMenuItem(submenu)"   @click="onMenuSelected(submenu)"  :class="{currentMenu : isSelected(submenu)}">{{submenu.label}}</a>
                                          <a href="#" v-if="isMenu(submenu)" @click="showSubMenu(submenu)">{{submenu.label}}<div  class="fa fa-caret-down right" :id="submenu.name"></div></a>
                                          <ul v-if="show(submenu)">
                                              <li v-for="menu02 of submenu.children">
                                                  <a href="#"   @click="onMenuSelected(menu02)" :class="{currentMenu : isSelected(menu02)}">{{menu02.label}}</a>
                                              </li>
                                          </ul>
                                      </li>
                                  </ul>
                              </li>
                          </ul>
                        </div>
                      </nav>
                  </section>`
});