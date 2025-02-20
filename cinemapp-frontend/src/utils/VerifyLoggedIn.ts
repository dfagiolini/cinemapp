import {useAuthStore} from "@/stores/auth.ts";

export function verifyLoggedIn(): boolean {
  if(useAuthStore().token != null) return true;
  else return false;
}
