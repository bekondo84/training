var sessionbody = Vue.component("s-body", {

    props: ["meta", "data"],
    data() {
       return {

       }
    }, methods: {
       register(item) {
          this.$emit("register-action", item);
       }
    }, computed : {
            datas() { return this.data== null ?  [] : this.data;}
    },created() {

    }, template: `<div class="card-container">
           <div class="card mb-3 card-margin" style="max-width: 450px;" v-for="d in datas">
             <div class="row g-0">
               <div class="col-md-12">
                 <div class="card-body">
                   <h5 class="card-title">{{d.training.name}}</h5>
                   <p class="card-text">{{d.training.fullDescription}}</p>
                   <p class="card-text"><small class="text-muted">date limite inscription : {{d.startAt}}</small></p>
                   <a href="#" class="btn btn-danger btn-sm" @click="register(d)">
                     register
                   </a>
                 </div>
               </div>
             </div>
           </div>
       </div>`
});