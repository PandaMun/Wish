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
            <div class="mx-2">Sign In</div>
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
                    <legend style="width: 0px"><span class="notranslate">​</span></legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input id="input-12" type="text" v-model="userId" placeholder="ID" />
                  </div>
                </div>
                <div class="v-text-field__details">
                  <div class="v-messages theme--light"><div class="v-messages__wrapper"></div></div>
                </div>
              </div>
            </div>
            <div
              class="v-input v-input--hide-details theme--light v-text-field v-text-field--is-booted v-text-field--enclosed v-text-field--outlined"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <fieldset aria-hidden="true">
                    <legend style="width: 0px"><span class="notranslate">​</span></legend>
                  </fieldset>
                  <div class="v-text-field__slot">
                    <input
                      id="input-15"
                      type="password"
                      v-model="userPassword"
                      placeholder="Password"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div
              class="v-input v-input--dense theme--light v-input--selection-controls v-input--checkbox"
            >
              <div class="v-input__control">
                <div class="v-input__slot">
                  <div class="v-input--selection-controls__input">
                    <i
                      aria-hidden="true"
                      class="v-icon notranslate v-icon--dense mdi mdi-checkbox-blank-outline theme--light"
                    ></i
                    ><input
                      aria-checked="false"
                      id="input-16"
                      role="checkbox"
                      type="checkbox"
                      value=""
                    />
                    <div class="v-input--selection-controls__ripple"></div>
                  </div>
                  <label
                    for="input-16"
                    class="v-label theme--light"
                    style="left: 0px; right: auto; position: relative"
                    >Remember me</label
                  >
                </div>
                <div class="v-messages theme--light"><div class="v-messages__wrapper"></div></div>
              </div>
            </div>
            <button
              type="button"
              class="success v-btn v-btn--block v-btn--is-elevated v-btn--has-bg theme--light v-size--x-large"
              @click="loginUser()"
            >
              <span class="v-btn__content">Sign In</span>
            </button>
            <br />
            <button
              type="button"
              class="secondary v-btn v-btn--block v-btn--is-elevated v-btn--has-bg theme--light v-size--x-large"
              @click="signUp()"
            >
              <span class="v-btn__content">Sign up</span>
            </button>
            <div class="text-center mt-2 text-body-2">
              <a href="/ui/landing/signin/signin1#" class="">Forgot your password?</a>
            </div>

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
// import http from "@/util/http-common";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      userId: "",
      userPassword: "",
      drawer: null,
      grantType: "",
      accessToken: "",
      refreshToken: "",
    };
  },

  methods: {
    ...mapActions(memberStore, ["userConfirm"]),
    loginUser() {
      let request = {
        userId: this.userId,
        password: this.userPassword,
      };

      this.userConfirm(request);
    },
  },
  // http.post("/wish/login", request).then(({ data }) => {
  //   if (typeof data.data.accessToken != "undefined") {
  //     VueCookies.set(
  //       "Authorization",
  //       data.data.grantType + " " + data.data.accessToken,
  //       "1200s"
  //     );
  //     VueCookies.set("RefreshToken", data.data.grantType + " " + data.data.grantType, "1200s");
  //     http.defaults.headers["Authorization"] = VueCookies.get("Authorization");
  //     http.defaults.headers["RefreshToken"] = VueCookies.get("RefreshToken");
  //     this.$router.push("/");
  //   } else {
  //     alert("아이디 혹은 비밀번호가 틀렸습니다.");
  //   }

  // );
  // console.log(VueCookies.get("Authorization"));
  // if (VueCookies.get("Authorization") != null) {
  //   this.$router.push("/");
  // }

  signUp() {
    this.$router.push("/signup");
  },
};
</script>
