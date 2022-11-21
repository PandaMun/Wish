<template>
  <v-footer dark padless>
    <v-card flat tile class="white lighten-1 black--text text-center" style="width: 100%">
      <v-card-text>
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 black--text" icon>
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="black--text pt-0"> 무언가 적을 곳</v-card-text>

      <v-divider></v-divider>

      <v-card-text class="black--text">
        {{ new Date().getFullYear() }} — <strong>SSAFY 8기</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
export default {
  data: () => ({
    icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
  }),
};
</script>
