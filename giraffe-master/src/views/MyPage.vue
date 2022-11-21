<template>
  <div
    class="pa-3 ma-4 mx-auto v-card v-sheet theme--light"
    style="max-width: 500px"
  >
    <div class="v-card__title">
      <hr
        role="separator"
        aria-orientation="horizontal"
        class="my-1 v-divider theme--light"
      />
      <div class="mx-2">My Page</div>
      <hr
        role="separator"
        aria-orientation="horizontal"
        class="my-1 v-divider theme--light"
      />
    </div>
    <div class="v-card__text">
      <div
        class="
          v-input
          theme--light
          v-text-field
          v-text-field--is-booted
          v-text-field--enclosed
          v-text-field--outlined
        "
      >
        <div class="v-input__control">
          <div class="v-input__slot">
            <fieldset aria-hidden="true">
              <legend style="width: 0px">
                <span class="notranslate">​</span>
              </legend>
            </fieldset>
            <div class="v-text-field__slot">
              <label
                for="input-12"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >User Id</label
              ><input id="input-12" type="text" />
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
        class="
          v-input
          theme--light
          v-text-field
          v-text-field--is-booted
          v-text-field--enclosed
          v-text-field--outlined
        "
      >
        <div class="v-input__control">
          <div class="v-input__slot">
            <fieldset aria-hidden="true">
              <legend style="width: 0px">
                <span class="notranslate">​</span>
              </legend>
            </fieldset>
            <div class="v-text-field__slot">
              <label
                for="input-15"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Last Name</label
              ><input id="input-15" type="text" />
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
        class="
          v-input
          theme--light
          v-text-field
          v-text-field--is-booted
          v-text-field--enclosed
          v-text-field--outlined
        "
      >
        <div class="v-input__control">
          <div class="v-input__slot">
            <fieldset aria-hidden="true">
              <legend style="width: 0px">
                <span class="notranslate">​</span>
              </legend>
            </fieldset>
            <div class="v-text-field__slot">
              <label
                for="input-18"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Email</label
              ><input id="input-18" type="text" />
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
        class="
          v-input
          theme--light
          v-text-field
          v-text-field--is-booted
          v-text-field--enclosed
          v-text-field--outlined
        "
      >
        <div class="v-input__control">
          <div class="v-input__slot">
            <fieldset aria-hidden="true">
              <legend style="width: 0px">
                <span class="notranslate">​</span>
              </legend>
            </fieldset>
            <div class="v-text-field__slot">
              <label
                for="input-21"
                class="v-label theme--light"
                style="left: 0px; right: auto; position: absolute"
                >Password</label
              ><input id="input-21" type="text" />
            </div>
          </div>
          <div class="v-text-field__details">
            <div class="v-messages theme--light">
              <div class="v-messages__wrapper"></div>
            </div>
          </div>
        </div>
      </div>
      <button
        type="button"
        class="
          success
          v-btn v-btn--block v-btn--is-elevated v-btn--has-bg
          theme--light
          v-size--x-large
        "
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
          class="
            flex-grow-1
            v-btn v-btn--outlined
            theme--light
            v-size--large
            secondary--text
            text--lighten-2
          "
        >
          <span class="v-btn__content"
            ><i
              aria-hidden="true"
              class="v-icon notranslate mdi mdi-facebook theme--light"
            ></i
          ></span></button
        ><button
          type="button"
          class="
            flex-grow-1
            mx-2
            v-btn v-btn--outlined
            theme--light
            v-size--large
            secondary--text
            text--lighten-2
          "
        >
          <span class="v-btn__content"
            ><i
              aria-hidden="true"
              class="v-icon notranslate mdi mdi-twitter theme--light"
            ></i
          ></span></button
        ><button
          type="button"
          class="
            flex-grow-1
            v-btn v-btn--outlined
            theme--light
            v-size--large
            secondary--text
            text--lighten-2
          "
        >
          <span class="v-btn__content"
            ><i
              aria-hidden="true"
              class="v-icon notranslate mdi mdi-google theme--light"
            ></i
          ></span>
        </button>
      </div>
      <div class="text-overline text-uppercase mt-3 text-center">
        <div class="v-responsive mx-auto" style="max-width: 280px">
          <div class="v-responsive__content">
            By signing up, you agree to the
            <a href="#">Terms of Service</a> &amp;
            <a href="#">Privacy Policy</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  