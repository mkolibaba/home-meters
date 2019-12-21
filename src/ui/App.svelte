<script>
  import { Router, Link, Route } from "svelte-routing";
  import LoginForm from "./forms/LoginForm.svelte";
  import RegisterForm from "./forms/RegisterForm.svelte";
  import NotFound from "./NotFound.svelte";
  import { onMount, setContext } from "svelte";
  import {
    key as userContextKey,
    initialValue as userContextInitialValue
  } from "./userContext";

  onMount(() => {
    setContext(userContextKey, userContextInitialValue);
  });

  const submit = ({ email, password }) =>
    fetch("/api/user")
      .then(response => response.json())
      .then(user => {
        setContext(userContextKey, {
          name: user.firstName,
          lastName: user.lastName,
          email: user.email
        });
      });
</script>

<style>
  section {
    height: 100vh;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(90deg, #00d2ff 0%, #3a47d5 100%);
  }
</style>

<Router>
  <section class="text-center">
    <Route path="/">
      <LoginForm {submit} />
    </Route>
    <Route path="/register" component={RegisterForm} />
    <Route component={NotFound} />
  </section>
</Router>
