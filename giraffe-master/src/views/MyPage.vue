<template>
  <div class="container col-5">
    <div class="my-6">
      <h2 class="text-h2 text-center font-weight-bold mt-5">My Page</h2>
    </div>
    <br />
    <form novalidate="novalidate" class="v-form">
      <div class="v-input theme--light v-text-field v-text-field--is-booted">
        <div class="v-input__control">
          <div class="v-input__slot">
            <div class="v-text-field__slot">
              <label
                for="input-9"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Id</label
              ><input
                v-model="id"
                style="padding-left: 100px"
                required="required"
                id="input-9"
                type="text"
                readonly
              />
            </div>
          </div>
        </div>
      </div>
      <div class="v-input theme--light v-text-field v-text-field--is-booted">
        <div class="v-input__control">
          <div class="v-input__slot">
            <div class="v-text-field__slot">
              <label
                for="input-9"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Name</label
              ><input
                v-model="name"
                required="required"
                style="padding-left: 100px"
                id="input-9"
                type="text"
                readonly
              />
            </div>
          </div>
        </div>
      </div>
      <div class="v-input theme--light v-text-field v-text-field--is-booted">
        <div class="v-input__control">
          <div class="v-input__slot">
            <div class="v-text-field__slot">
              <label
                for="input-9"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Address</label
              ><input
                v-model="address"
                required="required"
                style="padding-left: 100px"
                id="input-9"
                type="text"
                readonly
              />
            </div>
          </div>
        </div>
      </div>
      <div class="v-input theme--light v-text-field v-text-field--is-booted">
        <div class="v-input__control">
          <div class="v-input__slot">
            <div class="v-text-field__slot">
              <label
                for="input-12"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >E-mail</label
              ><input
                v-model="email"
                required="required"
                style="padding-left: 100px"
                id="input-12"
                type="text"
                readonly
              />
            </div>
          </div>
        </div>
      </div>
      <div class="v-input theme--light v-text-field v-text-field--is-booted">
        <div class="v-input__control">
          <div class="v-input__slot">
            <div class="v-text-field__slot">
              <label
                for="input-9"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Phone</label
              ><input
                v-model="phone"
                required="required"
                style="padding-left: 100px"
                id="input-9"
                type="text"
                readonly
              />
            </div>
          </div>
        </div>
      </div>

      <br />
      <div style="display: flex; justify-content: center">
        <!-- <button
          type="button"
          class="mr-4 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default success"
        >
          <span class="v-btn__content"> 확인 </span>
        </button> -->
        <button
          type="button"
          class="mr-4 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default error"
          @click="dialog = true"
        >
          <span class="v-btn__content"> 비밀번호 변경 </span>
        </button>
      </div>
    </form>
    <v-dialog persistent v-model="dialog" max-width="500px" :retain-focus="false">
      <v-card>
        <v-card-title>
          <span class="text-h5 font-weight-bold">새 비밀번호 입력</span>
        </v-card-title>
        <v-card-text class="pb-0">
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="password" required></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            class="font-weight-bold"
            text
            @click="modifyPassword('ssafy')"
          >
            변경
          </v-btn>
          <v-btn color="blue darken-1" class="font-weight-bold" text @click="dialog = false">
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialog: false,

      id: "ssafy",
      name: "김싸피",
      address: "장덕동",
      email: "ssafy@ssafy.com",
      phone: "010-1234-5678",
      password: "admin",
    };
  },
  methods: {
    getUserInfo(id) {
      let url = "wish/users/" + id;
      http.get(url).then(({ data }) => {
        this.id = data.id;
        this.name = data.name;
        this.address = data.address;
        this.email = data.email;
        this.phone = data.phoneNumber;
      });
    },
    modifyPassword(id) {
      let url = "wish/users/" + id;
      http.put(url, { password: this.password }).then(({ data }) => {
        console.log(data);
      });
    },
  },
};
</script>
