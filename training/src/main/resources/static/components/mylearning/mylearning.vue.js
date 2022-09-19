var myLearning = Vue.component("mylearning-component", {
   props : ["data", "meta", "menu"],
   data() {
      return {

      }
   }, created() {

       }, template: `<div class="card-container-width ">
        <session
            :meta="meta"
            :data="data"
            :menu="menu"
        ></session>
   </div>`
});