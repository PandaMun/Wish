<template>
  <div class="footer">
    <v-card absolute app color="primary" dark flat style="height:310px">
      <v-container>
        <v-row align="start">
          <v-col cols="12" md="3">
            <v-img :aspect-ratio="16 / 9"
            height="250px" src="../images/ssafy_logo.png" ></v-img>
            <!-- <h4 class="text-h5 font-weight-bold">
              SSAFY
            </h4> -->
          </v-col>

          <v-col cols="12" md="3">
            <v-list color="transparent" flat>
              <v-subheader class="text-h6">이용약관</v-subheader>
              <v-list-item-group color="primary">
                <v-list-item
                    v-for="(item, i) in heroui"
                    :key="i"
                    :href="item.href"
                    dense
                    target="_black"
                >
                  <v-list-item-content>
                    <v-list-item-title
                        class="text-body-1"
                        v-text="item.text"
                    ></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-col>

          <v-col cols="12" md="3">
            <v-list color="transparent" flat>
              <v-subheader class="text-h6">개인정보처리방침</v-subheader>
              <v-list-item-group color="primary">
                <v-list-item
                    v-for="(item, i) in shock"
                    :key="i"
                    :href="item.href"
                    dense
                    target="_black"
                >
                  <v-list-item-content>
                    <v-list-item-title
                        class="text-body-1"
                        v-text="item.text"
                    ></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-col>

          <v-col cols="12" md="3">
            <div>
              <h5 class="text-h6 font-weight-bold">
                About HH
              </h5>
              <p class="text-body-1">
                Welcome !
              </p>
            </div>

            
          </v-col>
        </v-row>

        <div class="text-center mt-6">
          Copyright &copy;
          <a
              class="white--text"
              href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp"
              rel="noopener noreferrer"
              target="_blank"
          >ssafy</a
          >
          {{ new Date().getFullYear() }}. All rights reserved.
        </div>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    heroui: [
      {
        text: "Github",
        href: "https://github.com/AGDholo",
      },
      {
        text: "Themes",
        href: "https://heroui.net/",
      },
    ],
    shock: [
      {
        text: "Download",
        href: "https://github.com/AGDholo/giraffe",
      },
      {
        text: "Issues",
        href: "https://github.com/AGDholo/giraffe/issues",
      },
      {
        text: "LICENSE",
        href: "https://github.com/AGDholo/giraffe/blob/master/LICENSE",
      },
    ],
  }),
};
</script>

<style >
.footer{
  width: 80%;
    position: absolute;  
    bottom: 0;
    left: 10%;
}
</style>