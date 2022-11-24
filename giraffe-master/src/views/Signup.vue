<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="auto" md="5" style="margin-top: 100px">
        <v-card max-width="700" class="justify-center">
          <v-card-title>
            <hr
              role="separator"
              aria-orientation="horizontal"
              class="my-1 v-divider theme--light"
            />
            <div class="mx-2">New Account</div>
            <hr
              role="separator"
              aria-orientation="horizontal"
              class="my-1 v-divider theme--light"
            />
          </v-card-title>
          <v-card-text>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate"> ​</span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input id="input-40" type="text" placeholder="ID" v-model="userId" />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light">
                    <div class="v-messages__wrapper"></div>
                  </div>
                </div>
              </div>
            </div>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate">​ </span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input
                      id="input-43"
                      placeholder="Password"
                      type="password"
                      v-model="userPassword"
                    />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light">
                    <div class="v-messages__wrapper"></div>
                  </div>
                </div>
              </div>
            </div>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate"> ​</span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input id="input-46" type="text" placeholder="Username" v-model="userName" />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light">
                    <div class="v-messages__wrapper"></div>
                  </div>
                </div>
              </div>
            </div>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate"> ​</span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input id="input-49" type="text" placeholder="Nickname" v-model="nickName" />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light"><div class="v-messages__wrapper"></div></div>
                </div>
              </div>
            </div>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate"> ​</span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input id="input-49" type="text" placeholder="Email" v-model="email" />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light"><div class="v-messages__wrapper"></div></div>
                </div>
              </div>
            </div>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate"> ​</span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input id="input-49" type="text" placeholder="Address" v-model="address" />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light"><div class="v-messages__wrapper"></div></div>
                </div>
              </div>
            </div>
            <div
              class="v-input theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px">
                      <span class="notranslate"> ​</span>
                    </legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input
                      id="input-49"
                      type="text"
                      placeholder="PhoneNumber"
                      v-model="phoneNumber"
                    />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light"><div class="v-messages__wrapper"></div></div>
                </div>
              </div>
            </div>
            <button
              type="button"
              class="success v-btn v-btn--block v-btn--is-elevated v-btn--has-bg theme--light v-size--x-large"
              @click="signUp()"
            >
              <span class="v-btn__content">Create Account</span>
            </button>
            <div class="d-flex my-3">
              <hr
                role="separator"
                aria-orientation="horizontal"
                class="my-1 v-divider theme--light"
              />
              <div class="text-overline mx-1">Or Sign In With</div>
              <hr
                role="separator"
                aria-orientation="horizontal"
                class="my-1 v-divider theme--light"
              />
            </div>
            <div class="d-flex justify-space-between">
              <button
                type="button"
                class="flex-grow-1 v-btn v-btn--outlined theme--light v-size--large secondary--text text--lighten-2"
              >
                <span class="v-btn__content"
                  ><i
                    aria-hidden="true"
                    class="v-icon notranslate mdi mdi-facebook theme--light"
                  ></i
                ></span></button
              ><button
                type="button"
                class="flex-grow-1 mx-2 v-btn v-btn--outlined theme--light v-size--large secondary--text text--lighten-2"
              >
                <span class="v-btn__content"
                  ><i aria-hidden="true" class="v-icon notranslate mdi mdi-twitter theme--light"></i
                ></span></button
              ><button
                type="button"
                class="flex-grow-1 v-btn v-btn--outlined theme--light v-size--large secondary--text text--lighten-2"
              >
                <span class="v-btn__content"
                  ><i aria-hidden="true" class="v-icon notranslate mdi mdi-google theme--light"></i
                ></span>
              </button>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import http from "@/util/http-common";
export default {
  data() {
    return {
      userId: "",
      userPassword: "",
      nickName: "",
      userName: "",
      email: "",
      phoneNumber: "",
      address: "",
      drawer: null,
    };
  },

  methods: {
    signUp() {
      let request = {
        userId: this.userId,
        password: this.userPassword,
        username: this.userName,
        nickname: this.nickName,
        email: this.email,
        phoneNumber: this.phoneNumber,
        address: this.address,
      };
      console.log(request);
      http.post("/wish/sign-up", request).then(({ data }) => {
        console.log(data);
        if (data.message == "success") {
          alert("회원가입 완료 되었습니다. 로그인해주세요");
          this.$router.push("/");
        }
      });
    },
  },
};
</script>
