<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;

/* preview_sql.twig */
class __TwigTemplate_353677c1de234ee4e7017177303b4e202d54c79deb449445c3314eb4e249add0 extends \Twig\Template
{
    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->parent = false;

        $this->blocks = [
        ];
    }

    protected function doDisplay(array $context, array $blocks = [])
    {
        // line 1
        echo "<div class=\"preview_sql\">
    ";
        // line 2
        if (twig_test_empty(($context["query_data"] ?? null))) {
            // line 3
            echo "        ";
            echo _gettext("No change");
            // line 4
            echo "    ";
        } elseif (twig_test_iterable(($context["query_data"] ?? null))) {
            // line 5
            echo "        ";
            $context['_parent'] = $context;
            $context['_seq'] = twig_ensure_traversable(($context["query_data"] ?? null));
            foreach ($context['_seq'] as $context["_key"] => $context["query"]) {
                // line 6
                echo "            ";
                echo PhpMyAdmin\Util::formatSql($context["query"]);
                echo "
        ";
            }
            $_parent = $context['_parent'];
            unset($context['_seq'], $context['_iterated'], $context['_key'], $context['query'], $context['_parent'], $context['loop']);
            $context = array_intersect_key($context, $_parent) + $_parent;
            // line 8
            echo "    ";
        } else {
            // line 9
            echo "        ";
            echo PhpMyAdmin\Util::formatSql(($context["query_data"] ?? null));
            echo "
    ";
        }
        // line 11
        echo "</div>
";
    }

    public function getTemplateName()
    {
        return "preview_sql.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  64 => 11,  58 => 9,  55 => 8,  46 => 6,  41 => 5,  38 => 4,  35 => 3,  33 => 2,  30 => 1,);
    }

    /** @deprecated since 1.27 (to be removed in 2.0). Use getSourceContext() instead */
    public function getSource()
    {
        @trigger_error('The '.__METHOD__.' method is deprecated since version 1.27 and will be removed in 2.0. Use getSourceContext() instead.', E_USER_DEPRECATED);

        return $this->getSourceContext()->getCode();
    }

    public function getSourceContext()
    {
        return new Source("", "preview_sql.twig", "C:\\AppServ\\www\\phpMyAdmin\\templates\\preview_sql.twig");
    }
}
