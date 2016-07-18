ocument).ready(function() {
        scrollIntoView("packages-nav");
        scrollIntoView("classes-nav");
        });
    </script>

     



<div class="col-12"  id="doc-col">

<div id="api-info-block">




<div class="sum-details-links">

</div><!-- end sum-details-links -->
<div class="api-level">
  
  Added in <a href="../../../guide/topics/manifest/uses-sdk-element.html#ApiLevels">API level 18</a>
  
  

</div>
</div><!-- end api-info-block -->


<!-- ======== START OF CLASS DATA ======== -->

<div id="jd-header">
    public
     
     
    
    interface
<h1 itemprop="name">TextDirectionHeuristic</h1>



  
  
  


    


</div><!-- end header -->

<div id="naMessage"></div>

<div id="jd-content" class="api apilevel-18">
<table class="jd-inheritance-table">


    <tr>
         	
        <td colspan="1" class="jd-inheritance-class-cell">android.text.TextDirectionHeuristic</td>
    </tr>
    

</table>







<div class="jd-descr">


<h2>Class Overview</h2>
<p itemprop="articleBody">Interface for objects that use a heuristic for guessing at the paragraph direction by examining text.
</p>





</div><!-- jd-descr -->
















<div class="jd-descr">


<h2>Summary</h2>



























<!-- ========== METHOD SUMMARY =========== -->
<table id="pubmethods" class="jd-sumtable"><tr><th colspan="12">Public Methods</th></tr>



	 
    <tr class="alt-color api apilevel-18" >
        <td class="jd-typecol"><nobr>
            abstract
            
            
            
            
            boolean</nobr>
        </td>
        <td class="jd-linkcol" width="100%"><nobr>
        <span class="sympad"><a href="../../../reference/android/text/TextDirectionHeuristic.html#isRtl(java.lang.CharSequence, int, int)">isRtl</a></span>(<a href="../../../reference/java/lang/CharSequence.html">CharSequence</a> cs, int start, int count)</nobr>
        
        <div class="jd-descrdiv">
          Guess if a <code>CharSequence</code> is in the RTL direction or not.
          
    

        </div>
  
  </td></tr>


	 
    <tr class=" api apilevel-18" >
        <td class="jd-typecol"><nobr>
            abstract
            
            
            
            
            boolean</nobr>
        </td>
        <td class="jd-linkcol" width="100%"><nobr>
        <span class="sympad"><a href="../../../reference/android/text/TextDirectionHeuristic.html#isRtl(char[], int, int)">isRtl</a></span>(char[] array, int start, int count)</nobr>
        
        <div class="jd-descrdiv">
          Guess if a chars array is in the RTL direction or not.
          
    

        </div>
  
  </td></tr>



</table>







</div><!-- jd-descr (summary) -->

<!-- Details -->








<!-- XML Attributes -->


<!-- Enum Values -->


<!-- Constants -->


<!-- Fields -->


<!-- Public ctors -->



<!-- ========= CONSTRUCTOR DETAIL ======== -->
<!-- Protected ctors -->



<!-- ========= METHOD DETAIL ======== -->
<!-- Public methdos -->

<h2>Public Methods</h2>



<A NAME="isRtl(java.lang.CharSequence, int, int)"></A>

<div class="jd-details api apilevel-18"> 
    <h4 class="jd-details-title">
      <span class="normal">
        public 
         
         
        abstract 
         
        boolean
      </span>
      <span class="sympad">isRtl</span>
      <span class="normal">(<a href="../../../reference/java/lang/CharSequence.html">CharSequence</a> cs, int start, int count)</span>
    </h4>
      <div class="api-level">
        <div>
  Added in <a href="../../../guide/topics/manifest/uses-sdk-element.html#ApiLevels">API level 18</a></div>
        
  

      </div>
    <div class="jd-details-descr">
      
    

      
  <div class="jd-tagdata jd-tagdescr"><p>Guess if a <code>CharSequence</code> is in the RTL direction or not.</p></div>
  <di