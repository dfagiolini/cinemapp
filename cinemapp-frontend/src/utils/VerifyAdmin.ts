import {useAuthStore} from "@/stores/auth.ts";
export function verifyAdmin() {
  return useAuthStore().isAdmin;
}
