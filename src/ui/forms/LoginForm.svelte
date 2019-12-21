<script>
  import { getContext } from "svelte";
  import { key as userContextKey } from "../userContext";

  export let submit;

  let email = "";
  let password = "";
  let isLoading = false;
  let isSuccess = false;
  let errors = {};
  let credentials = {};
  const handleSubmit = () => {
    errors = {};
    if (email.length === 0) {
      errors.email = "Field should not be empty";
    }
    if (password.length === 0) {
      errors.password = "Field should not be empty";
    }
    if (Object.keys(errors).length === 0) {
      isLoading = true;
      submit({ email, password })
        .then(() => {
          isSuccess = true;
          isLoading = false;
          credentials = getContext(userContextKey);
        })
        .catch(err => {
          errors.server = err;
          isLoading = false;
        });
    }
  };
</script>

<style src="./forms.css">

</style>

<form on:submit|preventDefault={handleSubmit}>
  {#if isSuccess}
    <div class="success">
      🔓
      <br />
      You've been successfully logged in as {credentials.name} {credentials.lastName}
      ({credentials.email}).
    </div>
  {:else}
    <h1>Log In</h1>

    <label class="hidden">Email</label>
    <input name="email" type="email" placeholder="Email" bind:value={email} />

    <label class="hidden">Password</label>
    <input
      name="password"
      type="password"
      placeholder="Password"
      bind:value={password} />

    <button type="submit">
      {#if isLoading}Logging in...{:else}Log in 🔒{/if}
    </button>

    <a href="/register">Register</a>

    {#if Object.keys(errors).length > 0}
      <ul class="errors">
        {#each Object.keys(errors) as field}
          <li>{field}: {errors[field]}</li>
        {/each}
      </ul>
    {/if}
  {/if}
</form>
