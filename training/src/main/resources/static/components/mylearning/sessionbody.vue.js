var sessionbody = Vue.component("s-body", {

    props: ["meta", "data"],
    data() {
       return {
          i18n : {}
       }
    }, methods: {
       register(item) {
          this.$emit("register-action", item);
       },unregister(item) {
           this.$emit("unregister-action", item);
       }, getMessage(key) {
           return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
         }
    }, computed : {
            datas() { return this.data== null ?  [] : this.data;}
    },async created() {
        try {
            let response = await axios.get("/api/v1/i18n?keys=register.btn,unregister.btn");
            this.i18n = response.data;
        } catch(error) {
           console.log(error);
        }
    }, template: `<div class="card-container">
           <div class="card mb-3 card-margin" style="max-width: 450px;" v-for="d in datas">
             <div class="row g-0">
               <div class="col-md-12">
                 <div class="card-body">
                   <h5 class="card-title">{{d.training.name}}</h5>
                   <p class="card-text">{{d.training.fullDescription}}</p>
                   <p class="card-text"><small class="text-muted">date limite inscription : {{d.startAt}}</small></p>
                   <a href="#" class="btn btn-success btn-sm" @click="register(d)" v-if="!d.registered">
                     {{getMessage('register.btn')}}
                   </a>
                   <a href="#" class="btn btn-danger btn-sm" @click="unregister(d)" v-if="d.registered">
                     {{getMessage('unregister.btn')}}
                   </a>
                 </div>
               </div>
             </div>
           </div>
       </div>`
});